package lab1.usecases;

import jakarta.ejb.Asynchronous;
import jakarta.enterprise.inject.Alternative;
import jakarta.ejb.AsyncResult;
import java.util.concurrent.Future;
import jakarta.ejb.Stateless;
import lab1.interceptors.LoggedInvocation;

@LoggedInvocation
@Alternative
@Stateless
public class AlternativeMovieReport extends GenerateMovieReport {
    @Override
    @Asynchronous
    public Future<String> generateReport() {
        return new AsyncResult<>("Ataskaita: alternative versija.");
    }
}