package org.FluffyTerror.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsManger {

        /**
         * Переменна для хранения данных считанных из файла properties и переданных пользователем
         * Т.е. переменная для хранения пользовательских properties
         *
         * @see Properties - реализован на основе {@link java.util.Hashtable}
         */
        private final Properties properties = new Properties();


        /**
         * Переменна для хранения объекта TestPropManager
         */
        private static PropsManger INSTANCE = null;


        /**
         * Конструктор специально был объявлен как private (singleton паттерн)
         * Происходит загрузка содержимого файла application.properties в переменную {@link #properties}
         *
         *
         */
        private PropsManger() {
            loadApplicationProperties();
        }


        /**
         * Метод ленивой инициализации TestPropManager
         *
         * @return TestPropManager - возвращает TestPropManager
         */
        public static PropsManger getPropsManager() {
            if (INSTANCE == null) {
                INSTANCE = new PropsManger();
            }
            return INSTANCE;
        }


        /**
         * Метод подгружает содержимого файла application.properties в переменную {@link #properties}
         * Либо из файла переданного пользователем через настройку -DpropFile={nameFile}

         */
        private void loadApplicationProperties() {
            try {
                properties.load(new FileInputStream(
                        new File("src/main/resources/" +
                                System.getProperty("propFile", "application") + ".properties")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Метод возвращает значения записанное в ключе в переменной {@link #properties}, если нет переменной вернет null
     *
     * @param key - ключ, значения которого хотите получить
     * @return String - строка со значением ключа
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}


