/**
 * Each record holds an integer key and a value of any type
 * (hence the need for a template).  The value type must be
 * printable using toString or this code will fail.
 *
 * In this implementation, the value is stored directly in
 * the record.  If the value is large, it would be more
 * efficient to have the records hold references to the values.
 * In this case, care would need to be taken to ensure
 * that deep copies are made upon assignment, and that
 * any values allocated are properly deleted in the Record
 * destructor.
 *
 */
package myhashmap;

/**
 *
 * @author timmermank1 modified from Michael Raymer
 */
// There are three types of records in a closed hash:
// Normal records, empty records, and tombstones
//Object that sets the record types.  Lets the RecordType be only normalRecord(occupied), emptyRecord(nothing is there), tombstone(item was there)
enum RecordType {
    normalRecord, emptyRecord, tombstone
};

public class Record<T> {

     private int keyInt;
     private String keyString;
    //Generic Data Type.  Takes the type T so that it's always usable for all object types
    private T value;
    private RecordType type;

    public Record() {
        keyInt = 0;
        type = RecordType.emptyRecord;
    }

    public Record(int newkey, T newValue) {
        this.keyInt = newkey;
        this.value = newValue;
        type = RecordType.normalRecord;
    }
    
    public Record(String newKey, T newValue){
        this.keyString = newKey;
        this.value = newValue;
        type = RecordType.normalRecord;
    }
    // Convert a record to a tombstone
    public void deleteRecord() {
        type = RecordType.tombstone;
    }

    // Get the integer key of a record
    public int getKeyInt() {
        return keyInt;
    }
    //Get the String key of a record
    public String getKeyString(){
        return keyString;
    }

    // Get the value of a record
    public T getValue() {
        return value;
    }

    // Check if a record is empty
    public boolean isEmpty() {
        return (type == RecordType.emptyRecord);
    }

    // Check if a record is a normal record
    public boolean isNormal() {
        return (type == RecordType.normalRecord);
    }

    // Check if a record is a tombstone
    public boolean isTombstone() {
        return (type == RecordType.tombstone);
    }

    // Overload the toString operator for printing records
    @Override
    public String toString() {
        if (isTombstone()) {
            return "<<Tombstone>>";
        } else if (isEmpty()) {
            return "<<Empty>>";
        } else {
            return "Key: " + keyString + ", Value: " + value;
        }
    }
 
}
