package ciclovix.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MapaMB {
		public String getTest() {
			return "Mensagem da ManagedBean.";
		}
}
