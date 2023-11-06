package data_access;

public class AtlasStudentDataAccessObject {
  private static final String atlasDataSourceName =
      System.getenv("ATLAS_DATA_SOURCE_NAME");
  private static final String atlasDatabaseName =
      System.getenv("ATLAS_DATABASE_NAME");
  private static final String atlasCollectionName = "students";
  private static final String atlasApiEndpoint =
      System.getenv("ATLAS_API_ENDPOINT");
  private static final String atlasApiKey = System.getenv("ATLAS_API_KEY");
}
