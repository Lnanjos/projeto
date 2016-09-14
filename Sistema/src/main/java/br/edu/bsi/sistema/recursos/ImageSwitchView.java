package br.edu.bsi.sistema.recursos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImageSwitchView {
     
    private List<String> images;
 
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
         
        images.add("nature1.jpg");
        images.add("nature2.jpg");
        images.add("nature3.jpg");
        images.add("nature4.jpg");
    }
 
    public List<String> getImages() {
        return images;
    }
}
