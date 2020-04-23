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
		//TODO: Create a DataAnalyzer object and pass the "AAPL" symbol

		
		// Create a CategoryAxis for the xAxis which says that our will xAxis will be of type string
		final CategoryAxis xAxis = new CategoryAxis();
		// Create a NumberAxis for the yAxis which says our yAxis will be of type Double, Long etc.. 
		final NumberAxis yAxis = new NumberAxis();
		
		TreeMap<Date, Double> data_AAL = da_AAL.getData();
		// TODO: Create a Hashmap object and retrieve the data from the DataAnalyzer for the "AAPL" data.

		
		ArrayList<Date>dates_AAL = da_AAL.getDates();
		
		//TODO: Create an ArrayList object and retrieve the dates from the DataAnalyzer for the "AAPL" data.

		//TODO: set the label for the xAxis
		xAxis.setLabel("Day");
		
		// Create the LineChart object passing in the xAxis and yAxis
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		//TODO: set the title of the linechart with your name and student id. for me it is: Feras Salous 113420589
		
		XYChart.Series series_AAL = new XYChart.Series();
		
		//TODO: create another series for the AAPL stock data.
		
		series_AAL.setName("AAL");
		
		// TODO: set the name of the AAPL series as "AAPL"

		for (Date date : dates_AAL) {

			series_AAL.getData()
					.add(new XYChart.Data(sd.format(date).toString(), data_AAL.get(date) ));
		}
		// TODO: Loop through the Dates of the Apple Data and fill the points for the series.
		
		

		//TODO: Create a Scene object named scene ,and pass in the lineChart, set the width = 800 and the height = to 600
		
		
		lineChart.getData().addAll(series_AAL);
		
		//TODO: Add the Apple series into the linechart.
		
		// TODO: set the stage scene to the scene object created above
		
		
		//TODO: show the stage

		
		//This takes an image of your gui, refresh eclipse to see the changes.
		String filename = "plots" + File.separator + "StockData.png";
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
