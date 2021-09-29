package storage.task;

public enum TaskType {
    BLANK, DEADLINE, TODO, EVENT, FDURATION, RECUR;

    /**
     * Constructor for 'TaskType' enum.
     */
    private TaskType() {
    }

    /**
     * Method to get all the types of this enum.
     *
     * @return A String Array that contains all the types of this enum
     */
    public static String[] getNames() {
        TaskType[] holder = TaskType.values();
        String[] returnArray = new String[holder.length];
        for (int index = 0; index < holder.length; ++index) {
            returnArray[index] = String.valueOf(holder[index]);
        }
        return returnArray;
    }
}
