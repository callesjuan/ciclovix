package br.ufes.inf.lprm.ciclovix.mb;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class SelectBooleanView {

   private boolean value1 = true;  
   private boolean value2 = true;

   public boolean isValue1() {
       return value1;
   }

   public void setValue1(boolean value1) {
       this.value1 = value1;
   }

   public boolean isValue2() {
       return value2;
   }

   public void setValue2(boolean value2) {
       this.value2 = value2;
   }
    
   public void addMessage() {
       String summary = value2 ? "Checked" : "Unchecked";
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
   }
}