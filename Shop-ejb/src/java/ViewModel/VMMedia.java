/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ViewModel;

import Entity.Media;
import flexjson.JSON;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anton
 */
public class VMMedia extends VMAbstract<VMMedia,Media> {
    
    private Integer id;
    public String FilePath;
    
    public VMMedia(Media media) {
        id = media.getIdMedia();
        FilePath = media.getFilePath();
    }

    VMMedia() {
        id = 0;
        FilePath = "";
    }
    
    @Override
    public VMMedia to(Media element) {
        return new VMMedia(element);
    }
   
    public static VMMedia getInstance() {
        return new VMMedia();
    }
}
