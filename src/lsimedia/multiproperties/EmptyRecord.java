/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lsimedia.multiproperties;

import java.util.ArrayList;
import javax.swing.JComponent;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author sbodmer
 */
public class EmptyRecord extends Record {

    public EmptyRecord() {
        super();
        
    }
    
    public EmptyRecord(Element record) {
        super(record);


    }

    //**************************************************************************
    //*** API
    //**************************************************************************    
    
    //**************************************************************************
    //*** Record
    //**************************************************************************
    @Override
    public RecordGUI getGUI(ArrayList<Column> columns) {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }
    
    @Override
    public void save(Element records) {
        Element e = records.getOwnerDocument().createElement("Empty");
        records.appendChild(e);
    }

    @Override
    public void swapColumn(int fromIndex, int toIndex) {
        //--- Nonthing here
    }
    
    @Override
    public void addColumn() {
        //--- Nothing here
    }
    
    @Override
    public void removeColumn(int index) {
        //--- Nothing here
    }
   
}