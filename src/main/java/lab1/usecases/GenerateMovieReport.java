package lab1.usecases;

import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import lab1.interceptors.LoggedInvocation;

import java.io.Serializable;
import java.util.concurrent.Future;

@LoggedInvocation
@Stateless
public class GenerateMovieReport implements Serializable {

    @Asynchronous
    public Future<String> generateReport() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            return new AsyncResult<>("Skaičiavimas buvo nutrauktas.");
        }
        return new AsyncResult<>("Ataskaita baigta." );
    }
}