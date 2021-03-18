import java.util.Optional;

public enum Comboboxes {
    ACCOUNTS {
        public String getString() {
            return "All Accounts";
        }
    },
    PROJECTS {
        public String getString() {
            return "All Projects";
        }
    },
    AREAS {
        public String getString() {
            return "All Areas";
        }
    },
    TECHNOLOGIES {
        public String getString() {
            return "All technologies";
        }
    };

    public String getString(){ return (String) Optional.empty().get(); }
}
