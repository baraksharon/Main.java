public class Room {
    private String name;
    private Item[] listItems;
    private Room[] roomsDirections;
    private boolean puzzleStatus;
    private Key roomkey;
    private boolean keyRoomStatus;


    /**
     * Constructs a new Room object with the specified name.
     *
     * @param name The name of the room.
     */
    public Room(String name) {
        this.name = name;
        this.listItems = new Item[2];
        this.roomsDirections = new Room[4];//north-0,south-1,east-2,west-3
        this.puzzleStatus = false;
        this.roomkey= null;
        this.keyRoomStatus = false;
    }

    /**
     * Gets the puzzle status of the room.
     *
     * @return The puzzle status of the room.
     */
    public boolean getPuzzleStatus() {
        return this.puzzleStatus;
    }

    /**
     * Sets the puzzle status of the room.
     *
     * @param status The puzzle status to set.
     */
    public void setPuzzleStatus(boolean status) {
        this.puzzleStatus = status;
    }

    /**
     * Sets the room key for this room.
     *
     * @param k The key to set for the room.
     */
    public void setRoomKey(Key k) {
        this.roomkey = k;
    }

    /**
     * Retrieves the room key for this room.
     *
     * @return The key associated with this room, or null if no key is set.
     */
    public Key getRoomKey() {
        return this.roomkey;
    }

    /**
     * Sets the status of whether this room requires a key to enter.
     *
     * @param status The status to set. True if a key is required, false otherwise.
     */
    public void setKeyRoomStatus(boolean status) {
        this.keyRoomStatus = status;
    }

    /**
     * Retrieves the status of whether this room requires a key to enter.
     *
     * @return True if a key is required to enter this room, false otherwise.
     */
    public boolean getKeyRoomStatus() {
        return this.keyRoomStatus;
    }

    /**
     * Adds an item to the room.
     *
     * @param i The item to add to the room.
     */
    public void addItemToRoom(Item i) {
        if (listItems[0] == null) {
            listItems[0] = i;
            System.out.println(listItems[0].getName() + " was added to the game.");
        } else if (listItems[1] == null) {
            listItems[1] = i;
            System.out.println(listItems[1].getName() + " was added to the game.");
        } else {
            System.out.println("Could not add " + i.getName() + " to the game.");
        }

    }
    /**
     * Removes the specified room from the array of room directions.
     * If the specified room is connected in the array of room directions,
     * sets its position in the array to null, effectively disconnecting it.
     *
     * @param r the room to be removed from the array of room directions
     */
    public void removeFromRoomDirection(Room r){
        for (int i=0;i<roomsDirections.length;i++){
            if(this.roomsDirections[i]!= null){
                if(this.roomsDirections[i].equals(r)){
                    this.roomsDirections[i]=null;
                }
            }
        }
    }

    /**
     * Removes the items from the room.
     *
     */
    public void removeItemFromRoom() {
        for (int i = 0; i < listItems.length; i++) {
            this.listItems[i] = null;
        }
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName() {
        return this.name;
    }

    /**
     * Gets the list of items in the room.
     *
     * @return The list of items in the room.
     */
    public Item[] getListItems(){return this.listItems;}

    /**
     * Establishes a connection between two rooms.
     *
     * @param other The other room to connect to.
     * @param d     The direction of the connection.
     */
    public void roomToRoomConnection(Room other,Direction d){
        int d1=-1,d2=-1;
        switch(d){
            case NORTH:
                d1=0;
                d2=1;
                break;
            case SOUTH:
                d1=1;
                d2=0;
                break;
            case EAST:
                d1=2;
                d2=3;
                break;
            case WEST:
                d1=3;
                d2=2;
                break;
        }
        if(this.getRoomsDirections()[d1]==null&& other.getRoomsDirections()[d2]==null){
            this.getRoomsDirections()[d1]=other;
            other.getRoomsDirections()[d2]=this;
            System.out.println(this.getRoomName()+" and "+other.getRoomName()+" are connected"+".");
        }
        else {System.out.println("Could not connect "+this.getRoomName()+" and "+other.getRoomName()+".");}
    }

    /**
     * Gets the directions of connected rooms.
     *
     * @return The array of connected rooms in different directions.
     */
    public Room[] getRoomsDirections() {return roomsDirections;}


    /**
     * Calculates the sum of values of items in the room.
     *
     * @return The sum of values of items in the room.
     */
    public int sumValuesOfRoomItems() {
        int sum = 0;
        if (this != null) {
            for (int i = 0; i < this.getListItems().length; i++) {
                if (this.getListItems()[i] != null) {
                    sum += this.getListItems()[i].getValue();
                }
            }
        }
        return sum;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param other The reference object with which to compare.
     * @return True if this object is the same as the other argument; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Room)) {
            return false;
        }
        Room otherRoom = (Room) other;
        return (this.getRoomName().equals(otherRoom.getRoomName()) &&
                this.getPuzzleStatus() == otherRoom.getPuzzleStatus() &&
                this.getKeyRoomStatus() == otherRoom.getKeyRoomStatus() &&
                this.sumValuesOfRoomItems() == otherRoom.sumValuesOfRoomItems());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = 19; // Different initial prime number
        int multiplier = 43; // Different multiplier
        result = multiplier * result + name.hashCode();
        result = multiplier * result + (puzzleStatus ? 1 : 0);
        result = multiplier * result + (keyRoomStatus ? 1 : 0);
        result = multiplier * result + sumValuesOfRoomItems();
        return result;
    }


}