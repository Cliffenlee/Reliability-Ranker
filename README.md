# Reliability-Ranker

Duration: 1 month  

Technologies used: Java, Venom web scraper, OctoParse  

About: Java program which takes in a dataset of sellers and listing from e-commerce websites (Carousell) and sort them based on reliability ratings determined by seller guidelines.


 To run the application:
 1. In line 22 of src/Algorithm/EntityConstructor.java, select one file from the specify the path to the CSV file (relative to the root folder) for our algorithm to be run against
 2. In line 68 of src/Algorithm/HeapSort.java, specify the path where the generated rankings will be placed
 3. Execute run.bat in root folder
 4. Check path specified in step 2 for generated rankings

 Preprocessing:
 To process words in reviews
 1. In line 11 of preprocessing/src/preprocessing/CountWords.java, specify path to the CSV file (relative to the /preprocessing folder) to be preprocessed
 2. In line 12, specify the path where the processed csv will be placed
 3. Execute run.bat in the /preprocessing folder
 4. Check path specified in step 2 for processed file

To run the stemmer: 
1. make sure the file path for editedoutput.csv is correct 
2. make sure the file path for finalWithReviews.csv is correct 
3. the main method should call for stemNegativewords() and stemReviewsOnly()
4. run maven package to package the project 
5. If you are using VSCode, use the leftmost play button to run the code.
