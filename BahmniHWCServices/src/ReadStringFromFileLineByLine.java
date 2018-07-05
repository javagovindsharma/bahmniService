
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class ReadStringFromFileLineByLine {
	public void importFileToDB(String sourceName, String fileName, String seprater, String tableName,
			String sourceFolder) {
		String successPath = "";
		String errorPath = "";
		String inputPath = "";
		try {

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(sourceFolder + "\\" + inputPath + fileName)));
			String line;
			int lineno = 0;
			String tableMetadat = "";
			// String value="";
			String[] metadata = null;

			while ((line = reader.readLine()) != null) {
				if (lineno == 0) {
					tableMetadat = line.replaceAll(seprater, ",");
					metadata = line.split("\\" + seprater);
					System.out.println("Table Metdata  " + metadata);

				} else {
					String[] fiedls = line.split("\\" + seprater);
					String value = "";
					String mdata = "";
					int count = 0;
					for (int i = 0; i < fiedls.length && i < metadata.length; ++i) {

						if ("".equals(fiedls[i])) {
							if (i == 0)
								value = value + null;
							else
								value = value + "," + null + "";
							if (i == 0)
								mdata = mdata + metadata[i];
							else
								mdata = mdata + "," + metadata[i] + "";
						} else {
							if (i == 0)
								value = value + "'" + fiedls[i] + "'";
							else
								value = value + ",'" + fiedls[i] + "'";
							if (i == 0)
								mdata = mdata + metadata[i];
							else
								mdata = mdata + "," + metadata[i] + "";
						}
					}

					System.out.println("insert into" + tableName + "(" + mdata + ")");
					System.out.println("values (" + value + ")");
				}
				System.out.println(lineno++);
			}

			System.out.println("complted");
			reader.close();
			Files.move(Paths.get(sourceFolder + "\\" + inputPath + fileName),
					Paths.get(sourceFolder + "\\" + successPath + fileName), StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
			try {
				Files.move(Paths.get(sourceFolder + "\\" + inputPath + fileName),
						Paths.get(sourceFolder + "\\" + errorPath + fileName), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}

	public static void main(String[] arg) {

		ReadStringFromFileLineByLine rd = new ReadStringFromFileLineByLine();
		rd.importFileToDB("SRC1", "Bandhan recon Extract.txt", "", "", "");
		rd.importFileToDB("SRC2", "Bandhan recon Extract.txt", "", "", "");

	}
}