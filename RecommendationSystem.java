import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load data from CSV file
            File dataFile = new File("data.csv");
            DataModel dataModel = new FileDataModel(dataFile);

            // Calculate similarity between users
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

            // Define a neighborhood for recommendations
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, dataModel);

            // Build the recommender system
            Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

            // Generate recommendations for a specific user
            int userId = 1; // Change to test for different users
            List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

            // Display the recommendations
            System.out.println("Recommendations for User " + userId + ":");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() + " | Predicted Rating: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
