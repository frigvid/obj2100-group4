package usn.obj2100.server.handler;

import usn.obj2100.server.controller.*;
import usn.obj2100.shared.model.Search;

import java.util.List;

public class SearchHandler
{
	private final InventarController inventarController;
	private final PlasseringController plasseringController;
	private final KategoriController kategoriController;
	private final KategoriTypeController kategoriTypeController;
	private final KassertController kassertController;
	private final KassertTypeController kassertTypeController;
	
	public SearchHandler()
	{
		inventarController = new InventarController();
		plasseringController = new PlasseringController();
		kategoriController = new KategoriController();
		kategoriTypeController = new KategoriTypeController();
		kassertController = new KassertController();
		kassertTypeController = new KassertTypeController();
	}

	private SearchHandler
	(
		InventarController inventarController,
		PlasseringController plasseringController,
		KategoriController kategoriController,
		KategoriTypeController kategoriTypeController,
		KassertController kassertController,
		KassertTypeController kassertTypeController
	)
	{
		this.inventarController = inventarController;
		this.plasseringController = plasseringController;
		this.kategoriController = kategoriController;
		this.kategoriTypeController = kategoriTypeController;
		this.kassertController = kassertController;
		this.kassertTypeController = kassertTypeController;
	}
	public List<Object> handleSearch(Search search)
	{
		return null;
	}

}
