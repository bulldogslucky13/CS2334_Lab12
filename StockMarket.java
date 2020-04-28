import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;


public class StockMarket extends Application {
	SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void start(Stage stage) throws IOException, ParseException {
		stage.setTitle("Stock Market Data");
		DataAnalyzer da_AAL = new DataAnalyzer("AAL");
		DataAnalyzer da_AAPL = new DataAnalyzer("AAPL");

		
		// Create a CategoryAxis for the xAxis which says that our will xAxis will be of type string
		final CategoryAxis xAxis = new CategoryAxis();
		// Create a NumberAxis for the yAxis which says our yAxis will be of type Double, Long etc.. 
		final NumberAxis yAxis = new NumberAxis();
		
		TreeMap<Date, Double> data_AAL = da_AAL.getData();
		TreeMap<Date, Double> data_AAPL = da_AAPL.getData();

		
		ArrayList<Date>dates_AAL = da_AAL.getDates();
		ArrayList<Date>dates_AAPL = da_AAPL.getDates();

		
		xAxis.setLabel("Day");
		
		// Create the LineChart object passing in the xAxis and yAxis
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("Cameron Bristol 113444055");
		
		XYChart.Series series_AAL = new XYChart.Series();
		XYChart.Series series_AAPL = new XYChart.Series();
		
		series_AAL.setName("AAL");
		series_AAPL.setName("AAPL");

		for (Date date : dates_AAL) {

			series_AAL.getData()
					.add(new XYChart.Data(sd.format(date).toString(), data_AAL.get(date) ));
		}

		for(Date date : dates_AAPL) {
			series_AAPL.getData()
				.add(new XYChart.Data(sd.format(date).toString(), data_AAPL.get(date)));
		}
		
		Scene scene = new Scene(lineChart, 800, 600);
		
		
		lineChart.getData().addAll(series_AAL);
		lineChart.getData().addAll(series_AAPL);
		
		stage.setScene(scene);
		stage.show();
		
		String filename = "src/plots" + File.separator + "StockData.png";
        saveScene(scene, filename);

	}
	 public static void saveScene(Scene scene, String filename) throws
     IOException {
		 // Convert the given Scene to an image that can be written to a file.
		 WritableImage image = scene.snapshot(null);
		 BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
		 // Create a file with the given name and save the image as a PNG.
		 File output = new File(filename);
		 ImageIO.write(bufferedImage, "png", output);
	 }
	 
	public static void main(String[] args) throws IOException, ParseException {
		launch(args);
	}
}
