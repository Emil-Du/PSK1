package lab1.usecases;

import jakarta.enterprise.inject.Specializes;
import jakarta.ejb.Stateless;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.AsyncResult;
import lab1.interceptors.LoggedInvocation;

import java.util.concurrent.Future;


@LoggedInvocation
@Specializes
@Stateless
public class SpecializedReport extends GenerateMovieReport {

    @Override
    @Asynchronous
    public Future<String> generateReport() {
        return new AsyncResult<>("Ataskaita: specialized versija.");
    }
}