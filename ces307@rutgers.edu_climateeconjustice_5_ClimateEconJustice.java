package climate;

import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered 
 * linked list structure that contains USA communitie's Climate and Economic information.
 * 
 * @author Navya Sharma
 */

public class ClimateEconJustice {

    private StateNode firstState;
    
    /*
    * Constructor
    * 
    * **** DO NOT EDIT *****
    */
    public ClimateEconJustice() {
        firstState = null;
    }

    /*
    * Get method to retrieve instance variable firstState
    * 
    * @return firstState
    * 
    * **** DO NOT EDIT *****
    */ 
    public StateNode getFirstState () {
        // DO NOT EDIT THIS CODE
        return firstState;
    }

    /**
     * Creates 3-layered linked structure consisting of state, county, 
     * and community objects by reading in CSV file provided.
     * 
     * @param inputFile, the file read from the Driver to be used for
     * @return void
     * 
     * **** DO NOT EDIT *****
     */
    public void createLinkedStructure ( String inputFile ) {
        
        // DO NOT EDIT THIS CODE
        StdIn.setFile(inputFile);
        StdIn.readLine();
        
        // Reads the file one line at a time
        while ( StdIn.hasNextLine() ) {
            // Reads a single line from input file
            String line = StdIn.readLine();
            // IMPLEMENT these methods
            addToStateLevel(line);
            addToCountyLevel(line);
            addToCommunityLevel(line);
        }
    }

    /*
    * Adds a state to the first level of the linked structure.
    * Do nothing if the state is already present in the structure.
    * 
    * @param inputLine a line from the input file
    */
    public void addToStateLevel ( String inputLine ) {
        String[] parts = inputLine.split(",");
        String stateName = parts[2]; // State name is at index 2

        // Check if the state already exists
        StateNode currentState = firstState;
        
        
        
        while (currentState != null) {
          
          
            if (currentState.getName().equals(stateName)) {
                // State already exists, do nothing
                return;
            }
            currentState = currentState.getNext();
        }

        // State does not exist, create a new StateNode and add it to the end of the list
     
        StateNode newState = new StateNode(stateName, null, null);
      
      
      
        if (firstState == null) {
            // The list is empty, set the new state as the first state
            firstState = newState;
        } else {
            // Find the last state in the list and add the new state after it
            currentState = firstState;
            while (currentState.getNext() != null) {
                currentState = currentState.getNext();
            }
            currentState.setNext(newState);
        }
    }

    /*
    * Adds a county to a state's list of counties.
    * 
    * Access the state's list of counties' using the down pointer from the State class.
    * Do nothing if the county is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCountyLevel ( String inputLine ) {
        String[] parts = inputLine.split(",");
        String stateName = parts[2]; // State name is at index 2
        String countyName = parts[1]; // County name is at index 1

        // Find the state
    StateNode currentState = firstState;
              while (currentState != null) {
          
                if (currentState.getName().equals(stateName)) {
                // State found, check if the county already exists in the state's list of counties
                CountyNode currentCounty = currentState.getDown();
                while (currentCounty != null) {
                    if (currentCounty.getName().equals(countyName)) {
                        // County already exists, do nothing
                        return;
                    }
                    currentCounty = currentCounty.getNext();
                }
                // County does not exist, create a new CountyNode and add it to the end of the list
                CountyNode newCounty = new CountyNode(countyName, null, null);
                if (currentState.getDown() == null) {
                    // The county list is empty, set the new county as the first county
                    currentState.setDown(newCounty);
                } else {
                    // Find the last county in the list and add the new county after it
                    currentCounty = currentState.getDown();
                    while (currentCounty.getNext() != null) {
                        currentCounty = currentCounty.getNext();
                    }
                    currentCounty.setNext(newCounty);
                }
                return;
            }
            currentState = currentState.getNext();
        }
    }

    /*
    * Adds a community to a county's list of communities.
    * 
    * Access the county through its state
    *      - search for the state first, 
    *      - then search for the county.
    * Use the state name and the county name from the inputLine to search.
    * 
    * Access the state's list of counties using the down pointer from the StateNode class.
    * Access the county's list of communities using the down pointer from the CountyNode class.
    * Do nothing if the community is already present in the structure.
    * 
    * @param inputFile a line from the input file
    */
    public void addToCommunityLevel ( String inputLine ) {
        String[] parts = inputLine.split(",");
        String stateName = parts[2]; // State name is at index 2
        String countyName = parts[1]; // County name is at index 1
        String communityName = parts[0]; // Community name is at index 0
        double prcntAfricanAmerican = Double.parseDouble(parts[3]); // Percent African American is at index 3
        double prcntNative = Double.parseDouble(parts[4]); // Percent Native is at index 4
        double prcntAsian = Double.parseDouble(parts[5]); // Percent Asian is at index 5
        double prcntWhite = Double.parseDouble(parts[8]); // Percent White is at index 8
        double prcntHispanic = Double.parseDouble(parts[9]); // Percent Hispanic is at index 9
        String disadvantaged = parts[19]; // Disadvantaged status is at index 19
        double PMlevel = Double.parseDouble(parts[49]); // PM Level is at index 49
        double chanceOfFlood = Double.parseDouble(parts[37]); // Chance of Flood is at index 37
        double prcntPovertyLine = Double.parseDouble(parts[121]); // Percent Poverty Line is at index 121

        // Find the state
        StateNode currentState = firstState;
        while (currentState != null) {
            if (currentState.getName().equals(stateName)) {
                // State found, find the county
                CountyNode currentCounty = currentState.getDown();
                while (currentCounty != null) {
                    if (currentCounty.getName().equals(countyName)) {
                        // County found, check if the community already exists in the county's list of communities
                        CommunityNode currentCommunity = currentCounty.getDown();
                        while (currentCommunity != null) {
                            if (currentCommunity.getName().equals(communityName)) {
                                // Community already exists, do nothing
                                return;
                            }
                            currentCommunity = currentCommunity.getNext();
                        }
                        // Community does not exist, create a new Data object and CommunityNode and add it to the end of the list
                        Data communityData = new Data(prcntAfricanAmerican, prcntNative, prcntAsian, prcntWhite, prcntHispanic, disadvantaged, PMlevel, chanceOfFlood, prcntPovertyLine);
                        CommunityNode newCommunity = new CommunityNode(communityName, null, communityData);
                        if (currentCounty.getDown() == null) {
                            // The community list is empty, set the new community as the first community
                            currentCounty.setDown(newCommunity);
                        } else {
                            // Find the last community in the list and add the new community after it
                            currentCommunity = currentCounty.getDown();
                            while (currentCommunity.getNext() != null) {
                                currentCommunity = currentCommunity.getNext();
                            }
                            currentCommunity.setNext(newCommunity);
                        }
                        return;
                    }
                    currentCounty = currentCounty.getNext();
                }
            }
            currentState = currentState.getNext();
        }
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int disadvantagedCommunities ( double userPrcntage, String race ) {
        int discombabulator = 0;
        StateNode currentState = firstState;
        while (currentState != null) {
            CountyNode currentCounty = currentState.getDown();
            while (currentCounty != null) {
                CommunityNode currentCommunity = currentCounty.getDown();
                while (currentCommunity != null) {
                    Data communityData = currentCommunity.getInfo();
                    double racePercentage = 0.0;
                    switch (race) {
                        case "African American":
                            racePercentage = communityData.getPrcntAfricanAmerican();
                            break;
                        case "Native American":
                            racePercentage = communityData.getPrcntNative();
                            break;
                        case "Asian American":
                            racePercentage = communityData.getPrcntAsian();
                            break;
                        case "White American":
                            racePercentage = communityData.getPrcntWhite();
                            break;
                        case "Hispanic American":
                            racePercentage = communityData.getPrcntHispanic();
                            break;
                        default:
                            break;
                    }
                    if (communityData.getAdvantageStatus().equals("True") &&
                            racePercentage * 100 >= userPrcntage) {
                                discombabulator++;
                    }
                    currentCommunity = currentCommunity.getNext();
                }
                currentCounty = currentCounty.getNext();
            }
            currentState = currentState.getNext();
        }
        return discombabulator;
    }

    /**
     * Given a certain percentage and racial group inputted by user, returns
     * the number of communities that have that said percentage or more of racial group  
     * and are identified as non disadvantaged
     * 
     * Percentages should be passed in as integers for this method.
     * 
     * @param userPrcntage the percentage which will be compared with the racial groups
     * @param race the race which will be returned
     * @return the amount of communities that contain the same or higher percentage of the given race
     */
    public int nonDisadvantagedCommunities ( double userPrcntage, String race ) {
        int guzelsayici = 0;
        StateNode currentState = firstState;
        while (currentState != null) {
            CountyNode currentCounty = currentState.getDown();
            while (currentCounty != null) {
                CommunityNode currentCommunity = currentCounty.getDown();
                while (currentCommunity != null) {
                    Data communityData = currentCommunity.getInfo();
                    double racePercentage = 0.0;
                    switch (race) {
                        case "African American":
                            racePercentage = communityData.getPrcntAfricanAmerican();
                            break;
                        case "Native American":
                            racePercentage = communityData.getPrcntNative();
                            break;
                        case "Asian American":
                            racePercentage = communityData.getPrcntAsian();
                            break;
                        case "White American":
                            racePercentage = communityData.getPrcntWhite();
                            break;
                        case "Hispanic American":
                            racePercentage = communityData.getPrcntHispanic();
                            break;
                        default:
                            break;
                    }
                    if (!communityData.getAdvantageStatus().equals("True") &&
                            racePercentage * 100 >= userPrcntage) {
                                guzelsayici++;
                    }
                    currentCommunity = currentCommunity.getNext();
                }
                currentCounty = currentCounty.getNext();
            }
            currentState = currentState.getNext();
        }
        return guzelsayici;
    }
    
    /** 
     * Returns a list of states that have a PM (particulate matter) level
     * equal to or higher than value inputted by user.
     * 
     * @param PMlevel the level of particulate matter
     * @return the States which have or exceed that level
     */ 
    public ArrayList<StateNode> statesPMLevels ( double PMlevel ) {
        ArrayList<StateNode> statesList = new ArrayList<>();
        StateNode currentState = firstState;
        while (currentState != null) {
            CountyNode currentCounty = currentState.getDown();
            while (currentCounty != null) {
                CommunityNode currentCommunity = currentCounty.getDown();
                while (currentCommunity != null) {
                    Data communityData = currentCommunity.getInfo();
                    if (communityData.getPMlevel() >= PMlevel & !statesList.contains(currentState)) {
                        statesList.add(currentState);
                        break; // Break once a community in the state exceeds the PMlevel
                    }
                    currentCommunity = currentCommunity.getNext();
                }
                currentCounty = currentCounty.getNext();
            }
            currentState = currentState.getNext();
        }
        return statesList;
    }

    /**
     * Given a percentage inputted by user, returns the number of communities 
     * that have a chance equal to or higher than said percentage of
     * experiencing a flood in the next 30 years.
     * 
     * @param userPercntage the percentage of interest/comparison
     * @return the amount of communities at risk of flooding
     */
    public int chanceOfFlood ( double userPercntage ) {
        int sayi = 0;
        StateNode currentState = firstState;
        while (currentState != null) {
            CountyNode currentCounty = currentState.getDown();
            while (currentCounty != null) {
                CommunityNode currentCommunity = currentCounty.getDown();
                while (currentCommunity != null) {
                    Data communityData = currentCommunity.getInfo();
                    if (communityData.getChanceOfFlood() >= userPercntage) {
                        sayi++;
                    }
                    currentCommunity = currentCommunity.getNext();
                }
                currentCounty = currentCounty.getNext();
            }
            currentState = currentState.getNext();
        }
        return sayi;
    }

    /** 
     * Given a state inputted by user, returns the communities with 
     * the 10 lowest incomes within said state.
     * 
     *  @param stateName the State to be analyzed
     *  @return the top 10 lowest income communities in the State, with no particular order
    */
    public ArrayList<CommunityNode> lowestIncomeCommunities ( String stateName ) {
        ArrayList<CommunityNode> lowestIncomeCommunities = new ArrayList<>();
        StateNode currentState = firstState;

        while (currentState != null && !currentState.getName().equalsIgnoreCase(stateName)) {
            currentState = currentState.getNext();
        }

        if (currentState == null) {
            // State not found
            return lowestIncomeCommunities;
        }

        CountyNode currentCounty = currentState.getDown();
        while (currentCounty != null) {
            CommunityNode currentCommunity = currentCounty.getDown();
            while (currentCommunity != null) {
                Data communityData = currentCommunity.getInfo();
                if (lowestIncomeCommunities.size() < 10) {
                    // Add the first 10 communities
                    lowestIncomeCommunities.add(currentCommunity);
                } else {
                    // Find the community with the highest income in the list
                    CommunityNode highestIncomeCommunity = lowestIncomeCommunities.get(0);
                    Data highestIncomeData = highestIncomeCommunity.getInfo();
                    for (CommunityNode community : lowestIncomeCommunities) {
                        Data communityIncomeData = community.getInfo();
                        if (communityIncomeData.getPercentPovertyLine() > highestIncomeData.getPercentPovertyLine()) {
                            highestIncomeCommunity = community;
                            highestIncomeData = communityIncomeData;
                        }
                    }
                    // Replace the community with the highest income if the current community has a lower income
                    if (communityData.getPercentPovertyLine() < highestIncomeData.getPercentPovertyLine()) {
                        lowestIncomeCommunities.remove(highestIncomeCommunity);
                        lowestIncomeCommunities.add(currentCommunity);
                    }
                }
                currentCommunity = currentCommunity.getNext();
            }
            currentCounty = currentCounty.getNext();
        }
        return lowestIncomeCommunities;
    }
}
    
