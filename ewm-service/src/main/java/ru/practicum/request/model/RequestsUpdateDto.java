package ru.practicum.request.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestsUpdateDto {
    @NotEmpty
    private List<Integer> requestIds;
    @NotNull
    private RequestStatus status;

    @Override
    public String toString() {
        return "RequestsUpdateDto{" +
                "requestIds=" + requestIds +
                ", status=" + status +
                '}';
    }
}
