package ch.fhnw.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.reactivestreams.Publisher;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Part06ReactiveVsBlocking {

    /**
     * TODO 06.01
     * <p>
     * Return the Customer emitted by the provided Mono
     */
    public Customer monoToCustomer(Mono<Customer> customer) {
        return customer.block();
    }

    /**
     * TODO 06.02
     * <p>
     * Return the Customers emitted by the provided Flux
     */
    public Iterable<Customer> fluxToCustomers(Flux<Customer> customers) {
        return customers.toIterable();
    }

    /**
     * TODO 06.03
     * <p>
     * Create a Flux reading all customers from the blocking {@link BlockingCustomerRepository}
     * deferred until the flux is subscribed.
     * <p>
     * Configure that each subscription will happen on a worker from Schedulers.elastic
     */
    public Flux<Customer> blockingRepositoryToFlux(BlockingCustomerRepository repository) {
        return Flux.defer(() -> Flux.fromIterable(repository.findAll()))
                .subscribeOn(Schedulers.elastic());
    }

    /**
     * TODO 06.04
     * <p>
     * Save the users from the customers Flux into the blocking repository using an elastic scheduler and return a
     * Mono<Void>
     */
    public Mono<Void> fluxToBlockingRepository(Flux<Customer> customers, BlockingCustomerRepository repository) {
        return customers
                .publishOn(Schedulers.elastic())
                .doOnNext(repository::save).then(); 

    }

    static class BlockingCustomerRepository {

        private ReactiveCustomerRepository repository = new ReactiveCustomerRepository();
        private long count;

        public Iterable<Customer> findAll() {
            count++;
            return repository.findAll().toIterable();
        }

        public void save(Customer customer) {
            count++;
            repository.save(Mono.just(customer)).block();
        }

        public long getCount() {
            return count;
        }
    }

    static class ReactiveCustomerRepository {

        private List<Customer> customers = new ArrayList<>();

        public ReactiveCustomerRepository() {
            customers.add(new Customer("John"));
            customers.add(new Customer("Jane"));
        }

        public Flux<Customer> findAll() {
            return Flux.fromIterable(customers);
        }

        public Mono<Void> save(Publisher<Customer> flux) {
            return Flux.from(flux).doOnNext(customer -> customers.add(customer)).then();
        }

    }

    @Data
    @RequiredArgsConstructor
    @EqualsAndHashCode
    static class Customer {
        final String name;

      public Customer(String theJane) {
        name = theJane;
      }


      @Override
      public boolean equals(Object atheO) {
        if (this == atheO)
          return true;
        if (!(atheO instanceof Customer))
          return false;
        Customer aaCustomer = (Customer) atheO;
        return Objects.equals(name, aaCustomer.name);
      }

      @Override
      public int hashCode() {
        return Objects.hash(name);
      }
    }
}
