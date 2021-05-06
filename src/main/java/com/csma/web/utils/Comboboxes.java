package com.csma.web.utils;

public enum Comboboxes {
    ACCOUNT {
        public String getString() {
            return "Account";
        }
    },
    PROJECT {
        public String getString() {
            return "Project";
        }
    },
    INDUSTRY {
        public String getString() {
            return "Industry";
        }
    },
    PRACTICE {
        public String getString() {
            return "Practice";
        }
    },
    TECHNOLOGY {
        public String getString(){
            return "Technology";
        }
    };

    public abstract String getString();
}
