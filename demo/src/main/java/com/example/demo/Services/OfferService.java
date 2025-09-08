package com.example.demo.Services;


import com.example.demo.entity.Offer;
import com.example.demo.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired private OfferRepository offerRepository;

    public Optional<Offer> validateOffer(String code){
        Optional<Offer> opt = offerRepository.findByCodeAndActiveTrue(code);
        if(opt.isPresent()){
            Offer o = opt.get();
            if(o.getValidTill() != null && o.getValidTill().isBefore(LocalDateTime.now())) return Optional.empty();
            return Optional.of(o);
        }
        return Optional.empty();
    }

    public Offer createOffer(Offer offer){ return offerRepository.save(offer); }
}

