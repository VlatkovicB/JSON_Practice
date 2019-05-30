import java.util.ArrayList;
import java.util.List;

import com.bojan.homework.model.Fixture;
import com.bojan.homework.model.Property;
import com.bojan.homework.model.Supply;
import com.bojan.homework.model.Totality;
import com.bojan.homework.service.JsonBuilder;

/**
 * Totality kao jedna celina, koja ima Supply sa svojim Fixturima. Jos cu sutra
 * preslusati detaljnije, da vidim da li nesto nedostaje. Verovatno bi moglo
 * bolje da se napravi, da pravi u posebnom folderu (da bude unique folder) za
 * svaki supply koji se doda.
 * 
 * @author Bojan
 *
 */
public class App {
	public static void main(String[] args) {
		Totality totality = null;
		ArrayList<Totality> totalities = new ArrayList<Totality>();

		/**
		 * Randomly building Totality of 5 supplies with 5 fixtures each.
		 */
		for (int j = 0; j < 5; j++) {

			int variable = (int) (Math.random() * 100);
			Supply supply = new Supply(variable, "supplyName" + variable,
					new ArrayList<Property>(), "supplyAddress" + variable,
					"supplyDescription");
			List<Fixture> fixtures = new ArrayList<Fixture>();
			for (int i = 0; i < 5; i++) {
				fixtures.add(new Fixture((int) (Math.random() * 100), "Name: "
						+ i + j, new ArrayList<Property>(), supply, "Desc: "
						+ i + j));
			}
			totality = new Totality(supply, fixtures, "deviceRelationsName" + j);
			totalities.add(totality);
		}

		Totality[] totalitiesArray = totalities.toArray(new Totality[totalities
				.size()]);

		JsonBuilder jb = JsonBuilder.getInstance();
		jb.setTotality(totalitiesArray);
	}
}
