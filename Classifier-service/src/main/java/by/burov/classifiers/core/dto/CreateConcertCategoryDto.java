package by.burov.classifiers.core.dto;

import javax.validation.constraints.NotNull;

public class CreateConcertCategoryDto {

        @NotNull
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}
