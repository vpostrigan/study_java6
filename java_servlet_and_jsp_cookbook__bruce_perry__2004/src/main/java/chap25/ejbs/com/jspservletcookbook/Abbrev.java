package chap25.ejbs.com.jspservletcookbook;

/*
  ** This file was automatically generated by EJBGen 2.11beta
  ** Build: 20021104-0000
*/

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Abbrev extends EJBObject {
    public String getAbbreviation(String state) throws RemoteException;
}
