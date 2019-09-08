package cz.czechitas.webapp.Service.Units;

public enum ModelType {
    INFANTRY {
        public String toString() {
            return "Infantry";
        }
    },
    CAVALRY {
        public String toString() {
            return "Cavalry";
        }
    },
    BEAST {
        public String toString() {
            return "Beast";
        }
    },
    CONSTRUCT {
        public String toString() {
            return "Construct";
        }
    }
}
