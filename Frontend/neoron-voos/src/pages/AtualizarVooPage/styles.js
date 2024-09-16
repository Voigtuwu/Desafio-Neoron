import styled from "styled-components";
import DatePicker from "react-datepicker";

export const Container = styled.div`
  padding: 20px;
  max-width: 1366px;
  margin: auto;
  background-color: #F9F9F9;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);

  /* Responsividade */
  @media (max-width: 768px) {
    padding: 15px;
    max-width: 100%;
  }

  @media (max-width: 480px) {
    padding: 10px;
    max-width: 100%;
  }
`;

export const Title = styled.h1`
  margin-bottom: 20px;
  font-size: 2rem;
  color: #333;
  text-align: center;

  /* Responsividade */
  @media (max-width: 768px) {
    font-size: 1.5rem;
  }

  @media (max-width: 480px) {
    font-size: 1.2rem;
  }
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  align-items: left;
  gap: 20px;

  /* Responsividade */
  @media (max-width: 768px) {
    gap: 15px;
  }

  @media (max-width: 480px) {
    gap: 10px;
  }
`;

export const Label = styled.label`
  font-size: 1rem;
  margin-bottom: 5px;
  color: #333;

  /* Responsividade */
  @media (max-width: 768px) {
    font-size: 0.9rem;
  }

  @media (max-width: 480px) {
    font-size: 0.8rem;
  }
`;

export const Select = styled.select`
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #CCC;
  background-color: #FFF;
  margin-left: 10px;

  /* Responsividade */
  @media (max-width: 768px) {
    padding: 8px;
    font-size: 0.9rem;
  }

  @media (max-width: 480px) {
    padding: 6px;
    font-size: 0.8rem;
  }
`;

export const Input = styled.input`
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #CCC;
  background-color: #FFF;
  margin-left: 10px;

  /* Responsividade */
  @media (max-width: 768px) {
    padding: 8px;
    font-size: 0.9rem;
  }

  @media (max-width: 480px) {
    padding: 6px;
    font-size: 0.8rem;
  }
`;

export const DatePickerStyled = styled(DatePicker)`
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid #CCC;
  background-color: #FFF;

  /* Responsividade */
  @media (max-width: 768px) {
    padding: 8px;
    font-size: 0.9rem;
  }

  @media (max-width: 480px) {
    padding: 6px;
    font-size: 0.8rem;
  }
`;

export const Button = styled.button`
  padding: 10px 20px;
  width: 200px;
  font-size: 1rem;
  color: #fff;
  background-color: #333;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3 ease;

  &:hover {
    background-color: orange;
  }

  /* Responsividade */
  @media (max-width: 768px) {
    width: 150px;
    font-size: 0.9rem;
  }

  @media (max-width: 480px) {
    width: 120px;
    font-size: 0.8rem;
  }
`;
