import styled from "styled-components";
import DatePicker from "react-datepicker";

export const Container = styled.div`
  padding: 20px;
  max-width: 800px;
  margin: auto;
`;

export const Title = styled.h1`
  margin-bottom: 20px;
`;

export const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 15px;
`;

export const Label = styled.label`
  font-size: 1rem;
  margin-bottom: 5px;
`;

export const Select = styled.select`
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid black;
`;

export const Input = styled.input`
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid black;
`;

export const DatePickerStyled = styled(DatePicker)`
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border-radius: 4px;
  border: 1px solid black;
`;

export const Button = styled.button`
  padding: 10px 20px;
  font-size: 1rem;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #0056b3;
  }
`;
