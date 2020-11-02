# Examples with *curl*

### Try to create Children Movie; should not work!
```shell
curl -X POST "http://localhost:8090/movierental/movies" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"title\":\"Just to test\",\"releaseDate\":\"2020-10-20\",\"rented\":false,\"priceCategory\":{\"id\":2,\"type\":\"ch.fhnw.eaf.rental.model.PriceCategoryChildren\"}}"
```

### Try to modify Movie to Children Movie; should not work!
```
curl -X PUT "http://localhost:8090/movierental/movies/20" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"title\":\"Just to test\",\"releaseDate\":\"2020-10-20\",\"rented\":false,\"priceCategory\":{\"id\":2,\"type\":\"ch.fhnw.eaf.rental.model.PriceCategoryChildren\"}}"
```

### Try to modify Movie to NewRelease Movie; should work!
```
curl -X PUT "http://localhost:8090/movierental/movies/20" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"title\":\"Just to test\",\"releaseDate\":\"2020-10-20\",\"rented\":false,\"priceCategory\":{\"id\":3,\"type\":\"ch.fhnw.eaf.rental.model.PriceCategoryNewRelease\"}}"
```