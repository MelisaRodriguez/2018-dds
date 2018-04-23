package Group10.TP_Anual;

import java.util.ArrayList;
import java.util.List;

public final class RepoCategorias {
	private static List<Categoria> categ = new ArrayList<>();
	
	public static void instanciarCategorias()
	{
		categ.add(new Categoria(18.76, 0.644, 0, 150));
		categ.add(new Categoria(35.32, 0.644, 151, 325));
		categ.add(new Categoria(60.71, 0.681, 326, 400));
		categ.add(new Categoria(71.74, 0.738, 401, 450));
		categ.add(new Categoria(110.38, 0.794, 451, 500));
		categ.add(new Categoria(220.75, 0.832, 501, 600));
		categ.add(new Categoria(443.59, 0.851, 601, 700));
		categ.add(new Categoria(545.96, 0.851, 701, 1400));
		categ.add(new Categoria(887.19, 0.851, 1401, Long.MAX_VALUE));
	}
	
	public static Categoria solicitarCategoria(double kwPorHora)
	{
		return categ.stream().filter(categoria -> categoria.estaEnLimites(kwPorHora)).findFirst().get();
	}
}
