package bll_validators;

public interface Validator<T> {
        /**
         *
         * @param t - the Class which will be taken as a parameter, in our case the Client
         * The headder of the method
         */
        public void validate(T t);
}
