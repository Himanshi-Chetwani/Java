/*
 * Name:
 *      Player.java
 * Version:
 *      1.0
 * Revisions:
 *      None
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Creates and defines a player who can be a client or a server
 */
public class Player {
    private String name;
    private Ship shipOne;
    private Ship shipTwo;

    private static final String COORDINATES = "COORDINATES: ";
    private static final String HIT = "HIT: ";
    static final String BOARD = "BOARD: ";
    private final String PAY_ATTENTION = "ATTENTION:";

    /**
     * Place client ships
     */
    private void placeClientShip() {
        this.shipOne = new Ship(2, 2, 3,
                2);
        this.shipTwo = new Ship(4, 4, 4,
                7);
    }

    /**
     * Place server ships
     */
    private void placeServerShips() {
        this.shipOne = new Ship(9, 1, 9,
                2);
        this.shipTwo = new Ship(5, 8, 8,
                8);
    }

    /**
     * Send message to client
     * @param client
     * @param message
     * @throws IOException
     */
    private void sendToClient(Socket client, String message) throws IOException {
        PrintWriter serverWriter =
                new PrintWriter(client.getOutputStream(), true);
        serverWriter.println(message);

    }

    /**
     * Name the client and send him the name
     * @param client
     * @throws IOException
     */
    private void nameTheClient(Socket client) throws IOException {
        String name = "Player-2";
        sendToClient(client, name);
    }

    /**
     * Split input string into an array, split on ','
     * @param input
     * @return
     */
    private String[] splitInput(String input) {
        String[] resultantCoordinates;
        resultantCoordinates = input.split(",");
        return resultantCoordinates;
    }

    /**
     * Get input from client
     * @param client
     * @return
     * @throws IOException
     */
    private String getClientInput(Socket client) throws IOException {
        String coordinates;
        BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(client.getInputStream()));
        coordinates = bufferedReader.readLine();
        return coordinates;
    }

    /**
     * Get server input
     * @return
     */
    private String getServerInput() {
        System.out.println("Player-1 enter coordinates in x,y form: ");
        Scanner serverInput = new Scanner(System.in);
        return serverInput.nextLine();
    }

    /**
     * Check health of both ships
     * @return
     */
    private boolean areShipsLost() {
        return shipOne.shipIsHit() && shipTwo.shipIsHit();
    }

    /**
     * Create and run server socket functionality
     * @param port
     */
    private void createServerSocket(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            this.name = "Player-1";
            placeServerShips();
            Board game = new Board();
            game.initializeBoard();
            game.printBoard();
            Socket client = server.accept();
            nameTheClient(client);
            sendToClient(client, game.toString());
            String coordinates, serverInputString = null;
            String clientMessage;

            boolean notHit = true;
            do {
                if (areShipsLost()) {
                    sendToClient(client, COORDINATES + "-1,-1");
                    client.close();
                    break;
                }
                if (notHit) {
                    String message = "Provide co-ordinates in x,y form";
                    sendToClient(client, message);      //SEND
                }

                clientMessage = getClientInput(client);
                if (clientMessage.contains(COORDINATES)) {
                    coordinates =
                            clientMessage.substring(COORDINATES.length());
                    String[] coordinateArray = splitInput(coordinates);
                    System.out.println("Received " + coordinates + ", ");
                    int x = Integer.parseInt(coordinateArray[0]);
                    int y = Integer.parseInt(coordinateArray[1]);
                    if (x == -1 & y == -1) {
                        server.close();
                        break;
                    }
                    if (game.checkIfMarkedAlready(x, y)) {
                        String duplicateAttack = PAY_ATTENTION + "Pay attention!"
                                + " You hit an already marked " +
                                " square and lost a chance!";
                        sendToClient(client, duplicateAttack);
                    }
                    attack(game, x, y);
                    System.out.println("Checking if server was hit");
                    checkIfServerHit(game, client, x, y);  //SEND
                    System.out.println("Sending board to client");
                    sendToClient(client, game.toString());  //SEND

                    if (areShipsLost()) {
                        sendToClient(client, COORDINATES + "-1,-1");
                        client.close();
                        break;
                    }
                    serverInputString = serverAttack(game, client);
                    notHit = true;
                    continue;
                }

                if (clientMessage.contains(HIT)) {
                    System.out.println(clientMessage);
                    String check = clientMessage;
                    check = check.substring(32);
                    char c = check.charAt(0);
                    int x = Integer.parseInt(String.valueOf(c));
                    check = check.substring(3);
                    System.out.println(check);
                    int y = Integer.parseInt(String.valueOf(check));
                    game.markShipHit(x, y);
                    System.out.println("Ship 1 health:" + shipOne.shipHealth);
                    System.out.println("Ship 2 health: " + shipTwo.shipHealth);
                    notHit = false;
                }

            } while (!serverInputString.equals("-1,-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Function for server to take a turn
     * @param game
     * @param client
     * @return
     * @throws IOException
     */
    private String serverAttack(Board game, Socket client) throws IOException {

        String serverInputString = getServerInput();
        String[] serverCoordinates = splitInput(serverInputString);
        System.out.println("ServerAttack: Server coordinates are "
                + serverInputString + ", ");
        System.out.println("Attacking with server coordinates");
        attack(game, Integer.parseInt(serverCoordinates[0]),
                Integer.parseInt(serverCoordinates[1]));

        System.out.println("Sending input coordinates to client");
        serverInputString = COORDINATES + serverInputString;
        sendToClient(client, serverInputString);    //SEND
        System.out.println("Sending board to client");
        sendToClient(client, game.toString());      //SEND

        System.out.println("Waiting for client message");
        return serverInputString;
    }

    /**
     * Check if server ships have been hit
     * @param boardObject
     * @param client
     * @param x
     * @param y
     * @throws IOException
     */
    private void checkIfServerHit(Board boardObject, Socket client, int x,
                                  int y) throws IOException {
        String message =
                HIT + name + "'s ship was hit at " + Integer.toString(x) +
                        " ," + Integer.toString(y);
        if ((this.shipOne.checkShipStatus(x, y)) ||
                this.shipTwo.checkShipStatus(x, y)) {
            System.out.println(message);
            boardObject.markShipHit(x, y);
            sendToClient(client, message);
        }
    }

    /**
     * Check if client ships have been hit
     * @param client
     * @param x
     * @param y
     * @throws IOException
     */
    private void checkClientHit(Socket client, int x,
                                int y) throws IOException {
        String message =
                HIT + this.name + "'s ship was hit at " + Integer.toString(x) +
                        " ," + Integer.toString(y);
        if ((this.shipOne.checkShipStatus(x, y)) ||
                this.shipTwo.checkShipStatus(x, y)) {
            System.out.println(message);
            sendToServer(client, message);
        }

    }

    /**
     * Attack based on player coordinates
     * @param boardObject
     * @param x
     * @param y
     */
    private void attack(Board boardObject, int x, int y) {
        boardObject.updateBoard(x, y);
        boardObject.printBoard();

    }

    /**
     * Print the board
     * @param reader
     * @throws IOException
     */
    private void readBoard(BufferedReader reader) throws IOException {
        System.out.println(reader.readLine());
        System.out.println(reader.readLine());
        for (int i = 0; i < 10; i++) {
            System.out.println(reader.readLine());
        }
        System.out.println();
    }

    /**
     * Send message to server
     * @param clientSocket
     * @param input
     * @throws IOException
     */
    private void sendToServer(Socket clientSocket, String input) throws
            IOException {
        PrintWriter printWriter =
                new PrintWriter(clientSocket.getOutputStream(),
                        true);
        printWriter.println(input);
    }

    /**
     * Get client input
     * @return
     */
    private String getClientInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    /**
     * Create and run client socket functionality
     * @param hostName
     * @param portNumber
     */
    private void createClientSocket(String hostName, int portNumber) {
        String input = null;
        String clientName;
        int serverCoordinateOne;
        int serverCoordinateTwo;
        String serverMessage;
        try {
            Socket clientSocket = new Socket(hostName, portNumber);
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader
                            (clientSocket.getInputStream()));
            clientName = bufferedReader.readLine();
            name = clientName;
            System.out.println("Your name is: " + clientName);
            placeClientShip();
            System.out.println(bufferedReader.readLine());
            readBoard(bufferedReader);
            do {
                if (areShipsLost()) {
                    sendToServer(clientSocket, COORDINATES + "-1,-1");
                    break;
                }
                System.out.println("Waiting for server");
                serverMessage = bufferedReader.readLine();
                if (serverMessage.contains("Provide")) {
                    System.out.println(serverMessage);
                    input = getClientInput();
                    sendToServer(clientSocket, COORDINATES + input);
                } else if (serverMessage.contains(HIT)) {
                    System.out.println(serverMessage);
                } else if (serverMessage.contains(BOARD)) {
                    readBoard(bufferedReader);
                } else if (serverMessage.contains(COORDINATES)) {

                    String serverCoordinates =
                            serverMessage.substring(COORDINATES.length());
                    System.out.println("Received coordinates " +
                            serverCoordinates);
                    String[] serverCoordinate = splitInput(serverCoordinates);
                    serverCoordinateOne = Integer.parseInt(serverCoordinate[0]);
                    serverCoordinateTwo = Integer.parseInt(serverCoordinate[1]);
                    if (serverCoordinateOne == -1 || serverCoordinateTwo == -1) {
                        clientSocket.close();
                        break;
                    }
                    checkClientHit(clientSocket, serverCoordinateOne,
                            serverCoordinateTwo);

                } else if (serverMessage.contains(PAY_ATTENTION)) {
                    System.out.println(serverMessage);
                }
            } while (!input.contains("-1,-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Create client and server sockets based on command line arguments
     * @param args
     */
    private void createPlayers(String[] args) {
        String hostname = "ab";
        int port = 1212;
        if (args.length == 2) {
            port = Integer.parseInt(args[1]);
            createServerSocket(port);
        } else {
            for (int index = 0; index < args.length - 1; index++) {
                if (args[index].equals("-host")) {
                    hostname = args[index + 1];
                }
                if (args[index].equals("-port")) {
                    port = Integer.parseInt(args[index + 1]);
                }
            }
            createClientSocket(hostname, port);
        }
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        Player player = new Player();
        player.createPlayers(args);
    }
}