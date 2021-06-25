package com.example.cityexplorer.service.impl;

import com.example.cityexplorer.exception.ErrorMessages;
import com.example.cityexplorer.exception.NotFoundException;
import com.example.cityexplorer.model.City;
import com.example.cityexplorer.model.Transport;
import com.example.cityexplorer.repository.TransportRepository;
import com.example.cityexplorer.service.TransportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public List<Transport> getList(@NotNull City city) {
        log.info("Requested Transport list for {} ({})", city.getName(), city.getId());
        return transportRepository.findAllByCity(city);
    }

    @Override
    @Transactional(readOnly = true)
    @NotNull
    public Transport getById(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_ID.getErrorMessage());

        log.info("Requested the Transport with id: {}", id);
        return transportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.NOT_FOUND.getErrorMessage()));
    }

    @Override
    @Transactional
    @NotNull
    public Transport save(@NotNull Transport transport) {
        Assert.notNull(transport, ErrorMessages.NULL_TRANSPORT_OBJECT.getErrorMessage());

        log.info("Saving a new Transport");
        return transportRepository.save(transport);
    }

    @Override
    @Transactional
    @NotNull
    public Transport update(@NotNull Long id, @NotNull Transport transport) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_ID.getErrorMessage());
        Assert.notNull(transport, ErrorMessages.NULL_TRANSPORT_OBJECT.getErrorMessage());

        getById(id);

        log.info("Updating the Transport with id: {}", id);
        transport.setId(id);

        return transportRepository.save(transport);
    }

    @Override
    @Transactional
    @NotNull
    public void delete(@NotNull Long id) {
        Assert.notNull(id, ErrorMessages.NULL_TRANSPORT_ID.getErrorMessage());

        getById(id);

        log.info("Deleting the Transport with id: {}", id);
        transportRepository.deleteById(id);
    }
}
