package Day6;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupAnswers {

    private Map<String, Integer> listOfYesAnswers;
    private int numberOfPeopleInGroup;

    public GroupAnswers() {
    }

    public GroupAnswers(List<String> listOfYesAnswers, int numberOfPeopleInGroup) {
        this.listOfYesAnswers = createMap(listOfYesAnswers);
        this.numberOfPeopleInGroup = numberOfPeopleInGroup;
    }

    public Map<String, Integer> getListOfYesAnswers() {
        return listOfYesAnswers;
    }

    public void setListOfYesAnswers(Map<String, Integer> listOfYesAnswers) {
        this.listOfYesAnswers = listOfYesAnswers;
    }

    public int getNumberOfPeopleInGroup() {
        return numberOfPeopleInGroup;
    }

    public void setNumberOfPeopleInGroup(int numberOfPeopleInGroup) {
        this.numberOfPeopleInGroup = numberOfPeopleInGroup;
    }

    private Map<String, Integer> createMap(List<String> listOfStrings) {
        Map<String, Integer> result = new HashMap<>();
        for (String temp : listOfStrings) {
            if (result.containsKey(temp)) {
                result.put(temp, result.get(temp) + 1);
            } else {
                result.put(temp, 1);
            }
        }
        return result;
    }

    public int numerOfPeopleInGroupThatMadeTheSameAnswer() {
        int result = 0;
        Iterator it = this.listOfYesAnswers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (this.numberOfPeopleInGroup == (int) pair.getValue()) {
                result++;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return result;
    }
}
