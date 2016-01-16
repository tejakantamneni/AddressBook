package com.jags;

import java.util.List;

/**
 * Created by jparvathaneni on 12/20/15.
 */
public interface AddressHandler {

    public Address read();
    public String readSearchTerm();
    public String readDeleteUUID();
    public String readEditTerm();
    public String readUUIDToEdit();
    public void print(Address address);
    public void print(List<Address> addressList);

    Address readEdit(Address printMatchedAddress);
}
