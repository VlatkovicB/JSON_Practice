import java.util.ArrayList;
import java.util.List;

import com.bojan.homework.model.Fixture;
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

		for (int j = 0; j < 5; j++) {
			Supply supply = new Supply(2, "supplyName" + j, null,
					"supplyAddress" + j, "supplyHexserial", "supplyDescription");

			List<Fixture> fixtures = new ArrayList<Fixture>();
			for (int i = 0; i < 5; i++) {
				fixtures.add(new Fixture(Integer.parseInt(i + "" + j), "Name: "
						+ i + j, null, supply, "Serial" + i + j, "Desc: " + i
						+ j));
			}
			totality = new Totality(supply, fixtures, "deviceRelationsName" + j);
			totalities.add(totality);
		}
		System.out.println(totalities);
		Totality[] totalitiesArray = (Totality[]) totalities
				.toArray(new Totality[totalities.size()]);

		JsonBuilder jb = JsonBuilder.getInstance();
		jb.setTotality(totalitiesArray);
	}
}
