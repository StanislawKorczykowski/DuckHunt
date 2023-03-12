package GUI;

public class TimeSimulation extends Thread{

    int counter = 0;

    @Override
    public void run(){

        while(MyPanel.state == MyPanel.STATE.GAME){

            try {
                Thread.sleep(1000);
                counter++;
                System.out.println(counter);
            } catch (InterruptedException e) {}
            }
        }



    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter = 0;
    }
}