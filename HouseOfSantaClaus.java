import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HouseOfSantaClaus {

  static int minValue = 111111111;
  static int maxValue = 155555552;

  ArrayList<Integer> solutions = new ArrayList<>();
  List<Integer> digits = Arrays.asList(new Integer[9]);

  void solve() {
    for (int n = minValue; n <= maxValue; n++) {
      if (isSolution(n)) {
        addSolution(n);
      }
    }
    // flip all numbers because the house can also be mirrored
    int numberOfSolutions = solutions.size();
    for (int i = 0; i<numberOfSolutions; i++){
      solutions.add(reverseNumber(solutions.get(i)));
    }
  }

  boolean isSolution(int number) {
    numberToDigits(number);
    if (!checkValidRangeOfDigits()) {
      return false;
    }
    if (!checkSelfLoop()) {
      return false;
    }
    if (!checkValidEdge()) {
      return false;
    }
    return checkForDuplicatedEdges();
  }
  void addSolution(int solution){
    this.solutions.add(solution);
  }
  void numberToDigits(int number) {
    for (int i = 8; i >= 0; i--) {
      this.digits.set(i, number % 10);
      number /= 10;
    }
  }

  boolean checkValidRangeOfDigits() {
    for (int i = 0; i < 9; i++) {
      if (this.digits.get(i) == 0 || this.digits.get(i) > 5) {
        return false;
      }
    }
    return true;
  }

  boolean checkSelfLoop() {
    for (int i = 1; i != 9; i++) {
      if (Objects.equals(this.digits.get(i - 1), this.digits.get(i))) {
        return false;
      }
    }
    return true;
  }

  boolean checkValidEdge() {
    for (int i = 1; i != 9; i++) {

      int a = this.digits.get(i);
      int b = this.digits.get(i - 1);

      if ((a == 1 && b == 5) || (a == 5 && b == 1) ||
          (a == 2 && b == 5) || (a == 5 && b == 2)) {
        return false;
      }
    }

    return true;
  }

  boolean checkForDuplicatedEdges() {
    for (int i = 1; i != 9; i++) {

      int edge1 = this.digits.get(i - 1) * 10 + this.digits.get(i);
      int edge2 = this.digits.get(i) * 10 + this.digits.get(i - 1);

      for (int j = i; j != 8; j++) {
        int edge = this.digits.get(j) * 10 + this.digits.get(j + 1);
        if (edge == edge1 || edge == edge2) {
          return false;
        }
      }
    }
    return true;
  }

  public static int reverseNumber(int number){
    int reverse = 0;
    while(number != 0)
    {
      int remainder = number % 10;
      reverse = reverse * 10 + remainder;
      number = number/10;
    }
    return reverse;
  }

  void printSolution(){
    System.out.print("Anzahl an Möglichkeiten: ");
    System.out.println(solutions.size());
    System.out.println("Zeichenreihenfolgen: ");
    for (Integer solution : solutions) {
      System.out.println(solution);
    }
  }
}
