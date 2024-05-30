import { Loader } from '@googlemaps/js-api-loader';

const apiOptions = {
    apiKey: "Av. de las Ciudades, 10, 28903 Getafe, Madrid, Spain"
}

const loader = new Loader(apiOptions);

loader.then(() => {
    console.log('Maps JS API Loaded');
});