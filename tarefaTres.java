import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class tarefaTres {//(deposito)
    private int itens = 0;
    final int capacidade = 10;

    private int saldo;

    private int qtd;

    public synchronized void retirar() throws InterruptedException {//mudança pra void depois da tarefa 11
        //metodo antes da mudança t7
        /*if (items > 0) {
            items--;
            System.out.println("Caixa retirada: Sobram " + items + " caixas");
            return 1;
        }*/

        //Mudançat7
        /*try {
            Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (itens > 0) {
            itens--;
            System.out.println("Caixa retirada: Sobram " + itens + " caixas");
            return 1;
        }
        return 0;*/

        //Tarefa11
        while(itens == 0){
            wait();
        }
        itens--;
        System.out.println("Caixa retirada: sobram "+itens+" caixas");
        notifyAll();
    }

    public synchronized void colocar() throws InterruptedException {//mudança para void dps da tarefa11
        //Metodo antes da mudança T7
        /*if (itens < capacidade) {
            itens++;
            System.out.println("Caixa armazenada: Passaram a ser " + itens + " caixas");
            return 1;
        }*/
        //Mudança tarefa11
        /*try {
            Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (itens < capacidade) {
            itens++;
            System.out.println("Caixa armazenada: Passaram a ser " + itens + " caixas");
            return 1;
        }
        return 0;*/

        while (itens == capacidade){
            wait();
        }
        itens++;
        System.out.println("Caixa armazenada: passara a ser "+itens+" caixas");
        notifyAll();
    }

    public static class produtor implements Runnable {

        private tarefaTres dep;//deposito
        private int tempoProducao;

        public produtor(tarefaTres dep, int tempoProducao) {
            this.dep = dep;
            this.tempoProducao = tempoProducao;
        }

        @Override
        public void run() {
            while (true) {
                //synchronized (dep) {} mudança tarefa11
                try {
                    dep.colocar();
                    Thread.sleep(tempoProducao * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumidor extends Thread {
        /*
        private tarefaTres dep;
        private int tempoConsumo;

        public Consumidor(tarefaTres dep, int tempoConsumo) {
            this.dep = dep;
            this.tempoConsumo = tempoConsumo;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    dep.retirar();
                    Thread.sleep(tempoConsumo * 1000);
                    /*synchronized (dep) { mudança após tarefa11
                        int retirou = dep.retirar();
                        if (retirou == 1) {
                            System.out.println("Consumidor " + Thread.currentThread().getId() + " retirou uma caixa.");
                        } else {
                            System.out.println("Consumidor " + Thread.currentThread().getId() + " bloqueado à espera que existam caixas.");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
        //Mudança na classe consumidor tarefa16

        private tarefaTres deposito;
        private String metodo;

        public Consumidor(tarefaTres deposito, String metodo){
            this.deposito = deposito;
            this.metodo = metodo;
        }

        public void run(){
            while (true){
                if (metodo.equals("wait_notify")){
                    int valor = deposito.retirar_wait_notify();
                    }
                }
            }
        }

    }/*T5 - RESPOSTA = a escolha entre implementar o interface Runnable ou criar uma subclasse de Thread
    depende das necessidades específicas do projeto e do contexto em que a thread será usada.
    Ambas as abordagens têm vantagens e desvantagens, e é importante escolher a mais adequada para cada situação.*/

    public class Contador{
        private int valor = 0;

        public synchronized void incrementar(){
            valor++;
        }

        public synchronized int getValor(){
            return valor;
        }
    }

    public void incremenTarefa12() throws InterruptedException {
        Contador contador = new Contador();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (contador) {
                    contador.incrementar();
                    contador.notify();
                    try {
                        contador.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() ->{
           for (int i = 0; i<10; i++){
               synchronized (contador) {
                   contador.incrementar();
                   contador.notify();
                   try {
                       contador.wait();
                   }catch (InterruptedException e){
                       e.printStackTrace();
                   }
               }
           }
        });
        thread1.start();
        thread2.start();
        //
        thread1.join();
        thread2.join();
        //
        System.out.println(contador.getValor());
    }
    //tarefa13
    public synchronized void retirar_balking(int qtd){
        if (qtd > this.saldo){
            System.out.println("Não foi possível retirar "+ qtd +" reais, saldo insuficiente");
            return;
        }

        this.saldo -= qtd;
        System.out.println("Retirada de "+ qtd + " reais efetuada com sucesso. Saldo atual: "+saldo);
    }

    //tarefa14
    public class Buffer{
        private Queue<Integer> fila = new LinkedList<>();
        private int capacidade = 10;

        private final Lock lock = new ReentrantLock();
        private final Condition cond = lock.newCondition();

        public void adicionar(int valor){
            lock.lock();
            try {
                while (fila.size() == capacidade){
                    cond.await();
                }
                fila.add(valor);
                cond.signalAll();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public int retirar_guarded_suspension(){
            lock.lock();
            try {
                while (fila.size() == 0){
                    cond.await();
                }
                int valor = fila.poll();
                cond.signalAll();
                return valor;
            }catch (InterruptedException e){
                e.printStackTrace();
                return  -1;
            }finally {
                lock.unlock();
            }
        }
    }

    //tarfa15
    public synchronized void retirar_time_wait(Long mlsec){
        while (this.qtd == 0){
            try {
                this.wait(mlsec);

                if (this.qtd == 0){
                    System.out.println("Não foi possível realizar a retirada após " + mlsec +" ms");
                    return;
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                return;
            }
        }
        this.qtd--;
        System.out.println("Retirada realizada com sucesso. Quantidade atual: "+this.qtd);
    }

    public static void main(String[] args) {
        //exercicio antes da mudança no t7
       /* tarefaTres dep = new tarefaTres();
        produtor p = new produtor(dep, 2);
        Consumidor c1 = new Consumidor(dep, 1);
        Consumidor c2 = new Consumidor(dep, 1);
        Consumidor c3 = new Consumidor(dep, 1);*/

        //mudança.
        tarefaTres dep = new tarefaTres();
        produtor p1 = new produtor(dep, 100);
        produtor p2 = new produtor(dep, 100);
        produtor p3 = new produtor(dep, 100);
        Consumidor c1 = new Consumidor(dep, 100);
        Consumidor c2 = new Consumidor(dep, 100);
        Consumidor c3 = new Consumidor(dep, 100);

        p1.run();
        p2.run();
        p3.run();
        c1.run();
        c2.run();
        c3.run();
        System.out.println("Execução do main Classe Deposito(tarefaTrês) terminado.");
//arrancar o produtor
//...
//arrancar o consumidor
//...
        System.out.println("Execucao do main da classe Deposito terminada");

    }
    /*Tarefa 8 - os problemas que podem ocorrer são: a variavel itens é compartilhada entre todas as threads, sem mecanismo
     * de sincronização causando problemas de inconsistencia, colocar e retirar tbm não sçao sincronizados, a implementação
     * não limita o numero de threads, adicionando "synchronized" esse problema é parcialmente resolvido
     *
     * Tarefa 10 - Embora synchronized ajude a evitar conflitos ela não resolve tudo,
     * ela tem como suas limitações: deadlocks, overhead, complexidade e garantia fraca
     *
     * Tarefa 11 - Os métodos wait(), notify() e notifyAll() são métodos da classe Object em Java, que são usados em
     * programação concorrente para permitir a comunicação e coordenação entre threads.*/


}