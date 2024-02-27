package org.example.jakarta_labb.dto;

import java.time.LocalDateTime;
import java.util.List;

public record Movies(List<MovieDto> movieDtos, LocalDateTime updated){}
