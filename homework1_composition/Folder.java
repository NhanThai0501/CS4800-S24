/**
 * 
 */
package homework1_composition;

/**
 * @author NhanThai
 *
 */
import java.util.ArrayList;
import java.util.List;

public class Folder {
	private String name;
	private List<File> files = new ArrayList<>();
	private List<Folder> subFolders = new ArrayList<>();

	public Folder() {
		
	}

	public Folder(String name) {
		this.name = name;
	}

	public void setFolderName(String n) {
		this.name = n;

	}

	public String getFolderName() {
		return name;
	}

	public void addFile(File file) {
		files.add(file);
	}

	public void addSubFolder(Folder folder) {
		subFolders.add(folder);
	}

	public void deleteFile(File file) {
		files.remove(file);
	}

	public void deleteSubFolder(Folder folder) {
		subFolders.remove(folder);
	}

	public void print(String prefix) {
		System.out.println(prefix + "+ " + name);
		subFolders.forEach(subfolder -> subfolder.print(prefix + "   "));
		files.forEach(file -> file.print(prefix + "   "));
	}
}
