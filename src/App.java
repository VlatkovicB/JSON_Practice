import java.util.ArrayList;
import java.util.List;

import com.bojan.homework.model.Fixture;
import com.bojan.homework.model.Supply;
import com.bojan.homework.model.Totality;
import com.bojan.homework.service.JsonBuilder;
import com.google.gson.Gson;

public class App {
	public static void main(String[] args) {
		Gson gson = new Gson();

		Supply supply = new Supply(2, "name", null, "address", "hexserial",
				"description");

		List<Fixture> fixtures = new ArrayList<Fixture>();
		for (int i = 0; i < 5; i++) {
			fixtures.add(new Fixture(i, "Name: " + i, null, "Address: " + i,
					"Serial" + i, "Desc: " + i));
		}

		String json = gson.toJson(supply);

		System.out.println(json);

		JsonBuilder jb = JsonBuilder.getInstance();

		jb.writeJsonString(new Totality(supply, fixtures, "deviceRelationsName"));
	}
}
