package org.zkoss.zss.performance;

import java.io.*;

import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zss.api.*;
import org.zkoss.zss.api.model.Book;
import org.zkoss.zss.ui.Spreadsheet;

public class ImportComposer extends SelectorComposer<Component>{

	@Wire
	private Spreadsheet ss;
	static private String FILE_PATH = "/books/";
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		importFileByParameter();
	}

	private void importFileByParameter() throws IOException{
		String fileName = Executions.getCurrent().getParameter("file");
		File file = new File(WebApps.getCurrent().getRealPath(FILE_PATH+fileName+".xlsx"));
		if (file.exists()){
			Importer importer = Importers.getImporter();
			Book book = importer.imports(file, "test");
			ss.setBook(book);
		}
	}
}
