package com.jags;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

/**
 * Created by tejakantamneni on 1/18/16.
 */
public class FileStoreHandler {

    private static final Log LOG = LogFactory.getLog(FileStoreHandler.class);
    private static final String ADDRESS_FILE_PATH = FileUtils.getTempDirectoryPath() + "address.dat";


    public static List<Address> loadSavedAddress() throws Exception{
        File addressFile = new File(ADDRESS_FILE_PATH);
        if(addressFile.exists()){
            FileInputStream fis = new FileInputStream(addressFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<Address> addressList = (List<Address>) ois.readObject();
            LOG.info("found " + addressList.size() + " addresses in saved file.");
            return addressList;
        }else{
            LOG.warn("No existing address file found, safe to ignore if this is a first run.");
        }
        return null;
    }

    public static void saveAddrsToFile(List<Address> addressList){
        try {
            LOG.debug("Saving address to file [" + ADDRESS_FILE_PATH + "]");
            FileOutputStream fileOutputStream = new FileOutputStream(ADDRESS_FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(addressList);
            objectOutputStream.close();
            fileOutputStream.close();
            LOG.debug("address file saved successfully");
        }catch (IOException i){
            LOG.error("failed to save address file", i);
        }
    }

}
