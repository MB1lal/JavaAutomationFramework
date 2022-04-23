package utils;

public enum SharedStateConstants {

    GENERAL;

    public enum BACKEND {

        PET_ID;

        public enum PET_STORE {
            PET_ORDER_ID,
            PET_STORE_RESPONSE
        }
        public enum PET {
            PET_RESPONSE,
            PET_STATUS
        }
        public enum USERS {
            USERNAME,
            FIRST_NAME,
            LAST_NAME,
            USER_ID,
            EMAIL,
            PASSWORD,
            STATUS,
            PHONE,
            USER_RESPONSE
        }
    }

    public enum FRONTEND {
        URL,
        RESPONSE,
        WEBDRIVER,
        EXCEL_DATA,
        LINK_TEXT,
        DRIVER_TABS,
        CAST_AND_CREW
    }

}
