/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.silabsoft.cryptocoin.identifier;

/**
 * 
 * @author Silabsoft
 */
public class AddressIdentifierException extends Exception {

    /**
     *
     * @param message
     */
    public AddressIdentifierException(String message) {
        super(message);
    }

    /**
     *
     * @param cause
     */
    public AddressIdentifierException(Throwable cause) {
        super(cause);
    }

}
