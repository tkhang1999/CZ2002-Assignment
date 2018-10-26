/**
 * Enumeration for different types of Sessions
 */
public enum SessionOption {
    LECTURE {   //Lecture type

        @Override   //return "Lecture" String
        public String toString() {
            return "Lecture";
        }
    },
    TUTORIAL {  //Tutorial type

        @Override   //return "Tutorial" String
        public String toString() {
            return "Tutorial";
        }
    },
    LABORATORY {       //Lab type

        @Override   //return "Laboratory" String
        public String toString() {
            return "Laboratory";
        }
    };

    //return number of session types
    public static int getNumSessionType() {
        return 3;
    }
}