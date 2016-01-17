package com.jags.utils;

import com.jags.Address;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.List;

/**
 * Created by tejakantamneni on 1/17/16.
 */
public final class AddressBookUtil {
    private static final Log LOG = LogFactory.getLog(AddressBookUtil.class);

    public static List<Address> loadAddressListFromFile() throws IOException, ClassNotFoundException {
        File addressFile = new File(Wellknown.ADDRESS_FILE_PATH);
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

    public static void saveAddressListToFile(List<Address> addressList) throws IOException {
        try {
            LOG.debug("Saving address to file [" + Wellknown.ADDRESS_FILE_PATH + "]");
            FileOutputStream fileOutputStream = new FileOutputStream(Wellknown.ADDRESS_FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(addressList);
            objectOutputStream.close();
            fileOutputStream.close();
            LOG.debug("address file saved successfully");
        }catch (IOException i){
            LOG.error("failed to save address file", i);
            throw i;
        }
    }
}
