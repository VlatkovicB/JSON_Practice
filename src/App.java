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

		Supply supply = new Supply(2, "supplyName", null, "supplyAddress",
				"supplyHexserial", "supplyDescription");
		List<Fixture> fixtures = new ArrayList<Fixture>();
		for (int i = 0; i < 5; i++) {
			fixtures.add(new Fixture(i, "Name: " + i, null, supply, "Serial"
					+ i, "Desc: " + i));
		}

		Totality totality = new Totality(supply, fixtures,
				"deviceRelationsName");

		JsonBuilder jb = JsonBuilder.getInstance();
		jb.writeJsonString(totality);
	}
}
