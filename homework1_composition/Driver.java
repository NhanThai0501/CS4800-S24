/**
 * 
 */
package homework1_composition;

/**
 * @author NhanThai
 *
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        // Create a root folder
        System.out.println("\nInitial folder structure: ");
		Folder root = new Folder();
        root.setFolderName("php_demo1");

        // Root-level folders
        Folder sourceFiles = new Folder();
        
        Folder includePath = new Folder();
        Folder remoteFiles = new Folder();
        sourceFiles.setFolderName("Source Files");
        includePath.setFolderName("Include Path");
        remoteFiles.setFolderName("Remote Files");

        root.addSubFolder(sourceFiles);
        root.addSubFolder(includePath);
        root.addSubFolder(remoteFiles);

        Folder phalcon = new Folder();
        phalcon.setFolderName(".phalcon");
        sourceFiles.addSubFolder(phalcon);

        Folder app = new Folder();
        app.setFolderName("app");
        sourceFiles.addSubFolder(app);

        Folder cache = new Folder();
        cache.setFolderName("cache");
        sourceFiles.addSubFolder(cache);

        Folder publicFolder = new Folder();
        publicFolder.setFolderName("public");
        sourceFiles.addSubFolder(publicFolder);

        // Sub-folders under app
        Folder config = new Folder();
        Folder controllers = new Folder();
        Folder library = new Folder();
        Folder migrations = new Folder();
        Folder models = new Folder();
        Folder views = new Folder();

        config.setFolderName("config");
        controllers.setFolderName("controllers");
        library.setFolderName("library");
        migrations.setFolderName("migrations");
        models.setFolderName("models");
        views.setFolderName("views");
        
        app.addSubFolder(config);
        app.addSubFolder(controllers);
        app.addSubFolder(library);
        app.addSubFolder(migrations);
        app.addSubFolder(models);
        app.addSubFolder(views);
    

        // Files under app
        File f1 = new File();
        File f2 = new File();
        File f3 = new File();

        f1.setFileName(".htaccess");
        f2.setFileName("hrouter.php");
        f3.setFileName("index.html");

        sourceFiles.addFile(f1);
        sourceFiles.addFile(f2);
        sourceFiles.addFile(f3);

        // Print the file system hierarchy
        root.print("");

        System.out.println("\nRemoving folder 'app': ");
        sourceFiles.deleteSubFolder(app);
        root.print("");

        System.out.println("\nRemoving folder 'public': ");
        sourceFiles.deleteSubFolder(publicFolder);
        root.print("");


	}

}
