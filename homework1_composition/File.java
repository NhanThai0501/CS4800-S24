/**
 * 
 */
package homework1_composition;

/**
 * @author NhanThai
 *
 */
public class File {
	private String name;

	public File() {

	}

	public File(String name) {
		this.name = name;
	}

	public String getFileName() {
		return name;
	}

	public void setFileName(String name) {
		this.name = name;
	}

	public void print(String prefix) {
		System.out.println(prefix + ">> " + name);
	}
}
